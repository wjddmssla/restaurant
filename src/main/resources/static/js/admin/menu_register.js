window.onload = () => {
    ComponentEvent.getInstance().addClickEventRegisterButton();
    ComponentEvent.getInstance().addClickEventImgAddButton();
    ComponentEvent.getInstance().addChangeEventImgFile();
    ComponentEvent.getInstance().addClickEventImgRegisterButton();
    ComponentEvent.getInstance().addClickEventImgCancelButton();
}

const menuObj = {
    menuCode: "",
    menuName: "",
    day: "",
    meals: "",
    menuAge1: "",
    menuAge2: "",
    salesPride: "",
    explanation: ""
}

const fileObj = {
    files: new Array(),
    formData: new FormData()
}

class MenuRegisterApi {
    static #instance = null;
    static getInstance() {
        if(this.#instance == null) {
            this.#instance = new MenuRegisterApi();
        }
        return this.#instance;
    }

    registerMenu() {
        let successFlag = false;

        $.ajax({
            async: false,
            type: "post",
            url: "http://localhost:8000/api/admin/menu",
            contentType: "application/json",
            data: JSON.stringify(menuObj),
            dataType: "json",
            success: response => {
                successFlag = true;
            },
            error: error => {
                console.log(error);
                MenuRegisterService.getInstance().setErrors(error.responseJSON.data);
                
            }

        });

        return successFlag;
    }

    registerImg() {

        $.ajax({
            async: false,
            type: "post",
            url: `http://localhost:8000/api/admin/menu/${menuObj.menuCode}/images`,
            encType: "multipart/form-data",
            contentType: false,
            processData: false,
            data: fileObj.formData,
            dataType: "json",
            success: response => {
                alert("메뉴 이미지 등록 완료.");
                location.reload();
            },
            error: error => {
                console.log(error);
            }
        })
    }

}

class MenuRegisterService {
    static #instance = null;
    static getInstance() {
        if(this.#instance == null) {
            this.#instance = new MenuRegisterService();
        }
        return this.#instance;
    }

    setMenuObjValues() {
        const registerInputs = document.querySelectorAll(".register-input");

        menuObj.menuCode = registerInputs[0].value;
        menuObj.menuName = registerInputs[1].value;
        menuObj.day = registerInputs[2].value;
        menuObj.meals = registerInputs[3].value;
        menuObj.menuAge1 = registerInputs[4].value;
        menuObj.menuAge2 = registerInputs[5].value;
        menuObj.salesPride = registerInputs[6].value;
        menuObj.explanation = registerInputs[7].value;
    }

    setErrors(errors) {
        const errorMessages = document.querySelectorAll(".error-message");
        this.clearErrors();

        Object.keys(errors).forEach(key => {
            if(key == "menuCode") {
                errorMessages[0].innerHTML = errors[key];
            }else if(key == "menuName") {
                errorMessages[1].innerHTML = errors[key];
            }else if(key == "day") {
                errorMessages[2].innerHTML = errors[key];
            }else if(key == "meals") {
                errorMessages[3].innerHTML = errors[key];
            }else if(key == "menuAge1") {
                errorMessages[4].innerHTML = errors[key];
            }else if(key == "menuAge2") {
                errorMessages[5].innerHTML = errors[key];
            }else if(key == "salesPride") {
                errorMessages[6].innerHTML = errors[key];
            }else if(key == "explanation") {
                errorMessages[7].innerHTML = errors[key];
            }
        })
    }

    clearErrors() {
        const errorMessages = document.querySelectorAll(".error-message");
        errorMessages.forEach(error => {
            error.innerHTML = "";
        })
    }
}


class ImgFileService {
    static #instance = null;
    static getInstance() {
        if(this.#instance == null) {
            this.#instance = new ImgFileService();
        }
        return this.#instance;
    }

    getImgPreview() {
        const menuImg = document.querySelector(".menu-img");

        const reader = new FileReader();

        reader.onload = (e) => {
            menuImg.src = e.target.result;
        }

        reader.readAsDataURL(fileObj.files[0]);

    }
}


class ComponentEvent {
    static #instance = null;
    static getInstance() {
        if(this.#instance == null) {
            this.#instance = new ComponentEvent();
        }
        return this.#instance;
    }

    addClickEventRegisterButton() {
        const registerButton = document.querySelector(".register-button");

        registerButton.onclick = () => {
            MenuRegisterService.getInstance().setMenuObjValues();
            const successFlag = MenuRegisterApi.getInstance().registerMenu();
            
            if(!successFlag) {
                return;
            }

            if(confirm("메뉴 이미지를 등록하시겠습니까?")) {
                const imgAddButton = document.querySelector(".img-add-button");
                const imgCancelButton = document.querySelector(".img-cancel-button");
    
                imgAddButton.disabled = false;
                imgCancelButton.disabled = false;
            }else {
                location.reload();
            }
        }

    }

    addClickEventImgAddButton() {
        const imgFile = document.querySelector(".img-file");
        const addButton = document.querySelector(".img-add-button");

        addButton.onclick = () => {
            imgFile.click();
        }
    }

    addChangeEventImgFile() {
        const imgFile = document.querySelector(".img-file");

        imgFile.onchange = () => {
            const formData = new FormData(document.querySelector(".img-form"));
            let changeFlag = false;

            fileObj.files.pop();

            formData.forEach(value => {
                console.log(value);

                if(value.size != 0) {
                    fileObj.files.push(value);
                    changeFlag = true;
                }
            });

            if(changeFlag) {
                const imgRegisterButton = document.querySelector(".img-register-button");
                imgRegisterButton.disabled = false;

                ImgFileService.getInstance().getImgPreview();
                imgFile.value = null;
            }

        }
    }

    addClickEventImgRegisterButton() {
        const imgRegisterButton = document.querySelector(".img-register-button");

        imgRegisterButton.onclick = () => {
            fileObj.formData.append("files", fileObj.files[0]);
            MenuRegisterApi.getInstance().registerImg();
        }
    }

    addClickEventImgCancelButton() {
        const imgCancelButton = document.querySelector(".img-cancel-button");

        imgCancelButton.onclick = () => {
            if(confirm("정말로 메뉴 등록을 취소하시겠습니까?")) {
                location.reload();
            }
        }
    }
}