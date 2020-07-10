import React, {useState} from 'react';
import { Router, Switch} from 'react-router-dom';
import LoginView from "../Views/LoginView";
import {history} from "../utils/history";
import {AuthedContext} from "../utils/authed";
import PrivateRoute from "./PrivateRoute";
import LoginRoute from "./LoginRoute";
import ProcessDetail from "../Views/ProcessDetail";

export default function BasicRoute(props) {
    history.listen((location, action) => {
        // clear alert on location change
        console.log(location,action);
    });

    const [isAuthed, setIsAuthed] = useState(false);
    const [userInfo, setUserInfo] = useState({});

    function authedSuccess() {
        setIsAuthed(true);
    }

    return(
        <AuthedContext.Provider value={{isAuthed, authedSuccess, userInfo, setUserInfo}}>
            <Router history={history}>
                <Switch>
                    <PrivateRoute exact path="/" component={ProcessDetail}/>
                    <LoginRoute exact path="/login" component={LoginView}/>
                </Switch>
            </Router>
        </AuthedContext.Provider>
    );

}
