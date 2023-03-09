window.onload = () => {
    LoginEvent.getInstance().addLoginSubmitOnclickEvent();
}

class LoginApi {
    static #instance = null;
    static getInstance() {
        if(this.#instance == null) {
            this.#instance = new LoginApi();
        }
        return this.#instance;
    }

    login(admin) {
        $.ajax({
            async: false,
            type: "post",
            url: "/api/account/login",
            contentType: "application/json",
            data: JSON.stringify(user),
            dataType: "json",
            success: response => {
                console.log(response);
                alert("로그인 성공. 관리자 페이지로 이동합니다.");
                location.replace("/templates/admin/login_home");
            },
            error: error => {
                console.log(error);
                RegisterService.getInstance().setErrorMessage(error.responseJSON.data);
            }
        })
    }
}

class LoginEvent {
    static #instance = null;
    static getInstance() {
        if(this.#instance == null) {
            this.#instance = new LoginEvent();
        }
        return this.#instance;
    }

    addRegisterSubmitOnclickEvent() {
        const registerSubmit = document.querySelector(".register-submit");

        loginSubmit.onclick = () => {
            const adminIdValue = document.querySelectorAll(".login-inputs")[0].value;
            const adminPwValue = document.querySelectorAll(".login-inputs")[1].value;

            const admin = new Admin(adminIdValue, adminPwValue);

            LoginApi.getInstance().login(admin);
        }
    }
}

class admin {
    adminId = null;
    adminPw = null;

    constructor(adminId, adminPw) {
        this.adminId = adminId;
        this.adminPw = adminPw;
    }
}