var TRMSStorage = window.localStorage;

function loadPageHeader() {
    let headDiv = document.getElementById('page-header');

    let navbar = document.createElement('nav');
    navbar.classList.add('navbar');
    navbar.classList.add('navbar-expand-sm');
    navbar.classList.add('navbar-light');
    navbar.classList.add('bg-light');

    let container = document.createElement('div');
    container.classList.add('container-fluid');
    container.classList.add('d-flex');
    container.classList.add('justify-content-start');

    let titleNode = document.createElement('div');
    // titleNode.classList.add('navbar-header');
    titleNode.classList.add('navbar-brand');
    titleNode.appendChild(document.createTextNode('TRMS'));
    container.appendChild(titleNode);

    let navList = document.createElement('div');
    // navList.classList.add('nav');
    navList.classList.add('navbar-nav');
    // navList.style.display = 'inline';
    // navList.classList.add('list-inline');

    let empTab = document.createElement('a');
    empTab.classList.add('nav-link');
    empTab.href = "employee.html";
    empTab.appendChild(document.createTextNode("Employee Info"));
    navList.appendChild(empTab);
    let requestTab = document.createElement('a');
    requestTab.classList.add('nav-link');
    requestTab.href = "requests.html";
    requestTab.appendChild(document.createTextNode("Requests"));
    navList.appendChild(requestTab);
    let commTab = document.createElement('a');
    commTab.classList.add('nav-link');
    commTab.href = "comm.html";
    commTab.appendChild(document.createTextNode("Communication"));
    navList.appendChild(commTab);
    let logOutTab = document.createElement('a');
    logOutTab.classList.add('nav-link');
    logOutTab.href = "index.html";
    logOutTab.appendChild(document.createTextNode("Log Out"));
    navList.appendChild(logOutTab);

    container.appendChild(navList);
    navbar.appendChild(container);
    headDiv.appendChild(navbar);

    let pageTitle = document.getElementById('page-title');

    switch(pageTitle.innerText) {
        case 'Login':
            logOutTab.classList.add('active');
            break;
        case 'Employee':
            empTab.classList.add('active');
            break;
        case 'Requests':
            requestTab.classList.add('active');
            break;
        case 'Communication':
            commTab.classList.add('active');
            break;
    }
}

function logout() {
    // let xhttp = new XMLHttpRequest();
    //     console.log("Logging out...");

    //     xhttp.onreadystatechange = function() {
    //         if (this.readyState == 4 & this.status == 200) {
    //             console.log("Logout complete.")
    //         }
    //     }

    //     let url = `http://localhost:7000/index.html`;

    //     xhttp.open("GET", url, true);

    //     // xhttp.setRequestHeader('Content-Type', 'application/json');

    //     xhttp.send();

    //Clear the Storage.
    TRMSStorage.setItem("user", "{}");
    TRMSStorage.setItem("supervisor", null);
    TRMSStorage.setItem("department", null);
    TRMSStorage.setItem("beneficiaries", null);
    TRMSStorage.setItem("requests", null);
}

function verifyLogin() {
    // let xhttp = new XMLHttpRequest();
    // console.log("Verifying Login...")

    // let activePage = document.getElementsByClassName('active');
    // if (activePage == "Login") {
    //     return;
    // }

    // xhttp.onreadystatechange = function() {

    //     if (this.readyState == 4 & this.status == 200) {
    //         if (this.responseText == "{}") {
    //             let divNode = document.getElementById("result-div");
    //             divNode.innerText = "Please login to continue..."

    //             setTimeout( function() {
    //                 window.location.href = "index.html";
    //             }, 1000);
                
    //         } else {
    //             let user = this.responseText;
    //             TRMSStorage.setItem("user", user);

    //         }
    //     }
    // }

    // let url = `http://localhost:7000/verify`;

    // xhttp.open("GET", url, true);

    // xhttp.send();

    let user = TRMSStorage.getItem("user");

    if (user == "{}") {
        let divNode = document.getElementById("result-div");
        divNode.innerText = "Please login to continue..."

        setTimeout( function() {
            window.location.href = "index.html";
        }, 1000);
    }
}

function login() {
    let xhttp = new XMLHttpRequest();

    let username = document.getElementById('usernameInput').value;
    let password = document.getElementById('passwordInput').value;


    xhttp.onreadystatechange = function() {
        if (this.readyState == 4 & this.status == 200) {
            
            console.log("response= "+this.responseText);
            // let user = JSON.parse(this.responseText);
            let user = this.responseText;
            TRMSStorage.setItem("user", user);
            if (user != "{}") {
                setTimeout( function() {
                    window.location.href = "employee.html";
                }, 1000);
                // window.location.href = "employee.html";
            }
            
            
        }
    }

    let url = `http://localhost:7000/`;

    xhttp.open("POST", url, true);

    xhttp.setRequestHeader('Content-Type', 'application/json');

    let submission = {
        username: username,
        password: password
    }

    xhttp.send(JSON.stringify(submission));

}
