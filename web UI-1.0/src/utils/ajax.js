export const ajax = (url, data, callback) => {
    fetch("https://localhost:8888/" + url, {
        method: "POST",
        body: JSON.stringify(data),
        headers: {
            'Content-Type': 'application/json',
            'Authorization': 'Bearer eyJ0eXBlIjoiSldUIiwiYWxnIjoiSFMyNTYifQ.eyJpc3MiOiJTbmFpbENsaW1iIiwiaWF0IjoxNTk3ODM5MTUwLCJzdWIiOiJhZG1pbiIsImV4cCI6MTU5ODQ0Mzk1MH0.7Hk5i0xUGM-aqrdCde9CiIf9qriBE8OcDm-iLii3bMk'
        }
    })
        .then((response) => {
            return response.json();
        })
        .then((data) => {
            console.log(data);
            callback(data);
        })
        .catch((error) => {
            console.log(error);
        })
}

export const login = (data, callback) => {
    fetch("http://localhost:8888/login", {
        method: "POST",
        body: {id: "admin",openid: "admin"},
        headers: {
            'Content-Type': 'application/json'
        }
    })
        .then((response) => {
            return response.json();
        })
        .then((data) => {
            console.log(data);
            callback(data);
        })
        .catch((error) => {
            console.log(error);
        })
}

