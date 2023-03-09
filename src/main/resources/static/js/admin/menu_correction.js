window.onload = () => {
    MenuModificationService.getInstance().setMenuCode();
    MenuModificationService.getInstance().loadCategories();
    MenuModificationService.getInstance().loadMenuAndImageData();

    ComponentEvent.getInstance().addClickEventModificationButton();
    ComponentEvent.getInstance().addClickEventImgAddButton();
    ComponentEvent.getInstance().addChangeEventImgFile();
    ComponentEvent.getInstance().addClickEventImgModificationButton();
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

const imgObj = {
    imageId: null,
    menuCode: null,
    saveName: null,
    originName: null
}

const fileObj = {
    files: new Array(),
    formData: new FormData()
}

class MenuModificationApi {
    static #instance = null;
    static getInstance() {
        if(this.#instance == null) {
            this.#instance = new MenuModificationApi();
        }
        return this.#instance;
    }

    getMenuAndImage() {
        let responseData = null;

        $.ajax({
            async: false,
            type: "get",
            url: `http://localhost:8000/api/admin/menu/${menuObj.menuCode}`,
            dataType: "json",
            success: response => {
                responseData = response.data;
            },
            error: error => {
                console.log(error);
            }
        });

        return responseData;
    }

    modifyMenu() {
        let successFlag = false;

        $.ajax({
            async: false,
            type: "put",
            url: `http://localhost:8000/api/admin/menu/${menuObj.menuCode}`,
            contentType: "application/json",
            data: JSON.stringify(menuObj),
            dataType: "json",
            success: response => {
                successFlag = true;
            },
            error: error => {
                console.log(error);
                MenuModificationService.getInstance().setErrors(error.responseJSON.data);
            }
        });

        return successFlag;
    }

    removeImg() {
        let successFlag = false;

        $.ajax({
            async: false,
            type: "delete",
            url: `http://localhost:8000/api/admin/menu/${menuObj.menuCode}/image/${imgObj.imageId}`,
            dataType: "json",
            success: response => {
                successFlag = true;
            },
            error: error => {
                console.log(error);
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
                alert("메뉴 이미지 수정 완료.");
                location.reload();
            },
            error: error => {
                console.log(error);
            }
        })
    }

}

class MenuModificationService {
    static #instance = null;
    static getInstance() {
        if(this.#instance == null) {
            this.#instance = new MenuModificationService();
        }
        return this.#instance;
    }

    setMenuCode() {
        const URLSearch = new URLSearchParams(location.search);
        menuObj.menuCode = URLSearch.get("menuCode");
    }

    setMenuObjValues() {
        const modificationInputs = document.querySelectorAll(".modification-input");

        menuObj.menuCode = modificationInputs[0].value;
        menuObj.menuName = modificationInputs[1].value;
        menuObj.day = modificationInputs[2].value;
        menuObj.meals = modificationInputs[3].value;
        menuObj.menuAge1 = modificationInputs[4].value;
        menuObj.menuAge2 = modificationInputs[5].value;
        menuObj.salesPride = modificationInputs[6].value;
        menuObj.explanation = modificationInputs[7].value;
    }

    loadMenuAndImageData() {
        const responseData = MenuModificationApi.getInstance().getMenuAndImage();

        if(responseData.menuMst == null) {
            alert("해당 메뉴코드는 등록되지 않은 코드입니다.");
            history.back();
            return;
        }

        const modificationInputs = document.querySelectorAll(".modification-input");
        modificationInputs[0].value = responseData.adminMenu.menuCode;
        modificationInputs[1].value = responseData.adminMenu.menuName;
        modificationInputs[2].value = responseData.adminMenu.day;
        modificationInputs[3].value = responseData.adminMenu.meals;
        modificationInputs[4].value = responseData.adminMenu.menuAge1;
        modificationInputs[5].value = responseData.adminMenu.menuAge2;
        modificationInputs[6].value = responseData.adminMenu.salesPride;
        modificationInputs[7].value = responseData.adminMenu.explanation;

        if(responseData.menuImage != null){
            imgObj.imageId = responseData.menuImage.imageId;
            imgObj.menuCode = responseData.menuImage.menuCode;
            imgObj.saveName = responseData.menuImage.saveName;
            imgObj.originName = responseData.menuImage.originName;

            const menuImg = document.querySelector(".menu-img");
            menuImg.src = `http://localhost:8000/image/menu/${responseData.menuImage.saveName}`;
        }
    }

    setErrors(errors) {
        const errorMessages = document.querySelectorAll(".error-message");
        this.clearErrors();

        Object.keys(errors).forEach(key => {
            if(key == "menuCode") {
                errorMessages[0].innerHTML = errors[key];
            }else if(key == "menuName") {
                errorMessages[1].innerHTML = errors[key];
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

    addClickEventModificationButton() {
        const modificationButton = document.querySelector(".modification-button");

        modificationButton.onclick = () => {
            MenuModificationService.getInstance().setMenuObjValues();
            const successFlag = MenuModificationApi.getInstance().modifyMenu();
            
            if(!successFlag) {
                return;
            }

            MenuModificationService.getInstance().clearErrors();

            if(confirm("메뉴 이미지를 수정하시겠습니까?")) {
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
                const imgModificationButton = document.querySelector(".img-modification-button");
                imgModificationButton.disabled = false;

                ImgFileService.getInstance().getImgPreview();
                imgFile.value = null;
            }

        }
    }

    addClickEventImgModificationButton() {
        const imgModificationButton = document.querySelector(".img-modification-button");

        imgModificationButton.onclick = () => {
            fileObj.formData.append("files", fileObj.files[0]);

            let successFlag = true;

            if(imgObj.imageId != null) {
                successFlag = MenuModificationApi.getInstance().removeImg();
            }

            if(successFlag) {
                MenuModificationApi.getInstance().registerImg();
            }
            
        }
    }

    addClickEventImgCancelButton() {
        const imgCancelButton = document.querySelector(".img-cancel-button");

        imgCancelButton.onclick = () => {
            if(confirm("정말로 이미지 수정을 취소하시겠습니까?")) {
                location.reload();
            }
        }
    }
}