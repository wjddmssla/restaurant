window.onload = () => {
    ReservationService.getInstance().loadReservationList();
    ComponentEvent.getInstance().addClickEventSearchButton();
}

let searchObj = {
    page : 1,
    searchValue : "",
    order : "reserveId",
    limit : "Y",
    count : 8
}

class ReservationSearchApi {
    static #instance = null;
    static getInstance() {
        if(this.#instance == null) {
            this.#instance = new ReservationSearchApi();
        }
        return this.#instance;
    }

    getReservationList(searchObj) {
        let returnData = null;

        $.ajax({
            async: false,
            type: "get",
            url: "http://localhost:8000/api/admin/reservations",
            data: searchObj,
            dataType: "json",
            success: response => {
                console.log(response);
                returnData = response.data;
            },
            error: error => {
                console.log(error);
            }
        });

        return returnData;
    }

    getReservationTotalCount(searchObj) {
        let returnData = null;

        $.ajax({
            async: false,
            type: "get",
            url: "http://localhost:8000/api/admin/reservations/totalcount",
            data: {
                "searchValue" : searchObj.searchValue
            },
            dataType: "json",
            success: response => {
                console.log(response);
                returnData = response.data;
            },
            error: error => {
                console.log(error);
            }
        });

        return returnData;
    }
}


class ReservationService {
    static #instance = null;
    static getInstance() {
        if(this.#instance == null) {
            this.#instance = new ReservationService();
        }
        return this.#instance;
    }

    loadReservationList() {
        const responseData = ReservationSearchApi.getInstance().getReservationList(searchObj);

        const reservationListBody = document.querySelector(".reservation-table tbody");
        reservationListBody.innerHTML = "";

        responseData.forEach((data, index) => {
            reservationListBody.innerHTML += `
                <tr>
                    <td>${data.reserveId}</td>
                    <td>${data.reserveName}</td>
                    <td>${data.reserveDate}</td>
                    <td>${data.reserveTime}</td>
                    <td>${data.number}</td>
                    <td>${data.email}</td>
                    <td>${data.adult}</td>
                    <td>${data.child}</td>
                    <td>${data.guest}</td>
                    <td>${data.reqeust}</td>
                </tr>
            `;
        });

        this.loadSearchNumberList();
    }

    loadSearchNumberList() {
        const pageController = document.querySelector(".page-controller");

        const totalCount = ReservationSearchApi.getInstance().getReservationTotalCount(searchObj);
        const maxPageNumber = totalCount % searchObj.count == 0 
                            ? Math.floor(totalCount / searchObj.count) 
                            : Math.floor(totalCount / searchObj.count) + 1;

        pageController.innerHTML = `
            <a href="javascript:void(0)" class="pre-button disabled">이전</a>
            <ul class="page-numbers">
            </ul>
            <a href="javascript:void(0)" class="next-button disabled">다음</a>
        `;

        if(searchObj.page != 1) {
            const preButton = pageController.querySelector(".pre-button");
            preButton.classList.remove("disabled");

            preButton.onclick = () => {
                searchObj.page--;
                this.loadReservationList();
            }
        }

        if(searchObj.page != maxPageNumber) {
            const nextButton = pageController.querySelector(".next-button");
            nextButton.classList.remove("disabled");

            nextButton.onclick = () => {
                searchObj.page++;
                this.loadReservationList();
            }
        }

        const startIndex = searchObj.page % 5 == 0 
                        ? searchObj.page - 4 
                        : searchObj.page - (searchObj.page % 5) + 1;
        const endIndex = startIndex + 4 <= maxPageNumber ? startIndex + 4 : maxPageNumber;
        const pageNumbers = document.querySelector(".page-numbers");

        for(let i = startIndex; i <= endIndex; i++) {
            pageNumbers.innerHTML += `
                <a href="javascript:void(0)"class="page-button ${i == searchObj.page ? "disabled" : ""}"><li>${i}</li></a>
            `;
        }

        const pageButtons = document.querySelectorAll(".page-button");
        pageButtons.forEach(button => {

            const pageNumber = button.textContent;
            if(pageNumber != searchObj.page) {
                button.onclick = () => {
                    searchObj.page = pageNumber;
                    this.loadReservationList();
                }
            }
        });
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

    addClickEventSearchButton() {
        const searchInput = document.querySelector(".search-input");
        const searchButton = document.querySelector(".search-button");

        searchButton.onclick = () => {
            searchObj.category = categorySelect.value;
            searchObj.searchValue = searchInput.value;
            searchObj.page = 1;
            ReservationService.getInstance().loadReservationList();
        }

        searchInput.onkeyup = () => {
            if(window.event.keyCode == 13) {
                searchButton.click();
            }
        }
    }

}