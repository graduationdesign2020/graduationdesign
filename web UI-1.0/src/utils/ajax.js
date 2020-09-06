export const ajax = (url, data, callback) => {
    fetch("http://localhost:8888/" + url, {
        method: "POST",
        body: JSON.stringify(data),
        headers: {
            'Content-Type': 'application/json',
            'Authorization': localStorage.getItem("Authorization")
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
    fetch("http://10.162.186.199:8888/adminlogin", {
        method: "POST",
        body: JSON.stringify(data),
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

