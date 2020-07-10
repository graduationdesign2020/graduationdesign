import React, {useContext} from 'react';
import {Route, Redirect} from 'react-router-dom'
import {AuthedContext} from "../utils/authed";

export default function LoginRoute(props) {
    const {isAuthed} = useContext(AuthedContext);
    const {component: Component, path="/",exact=false,strict=false} = props;

    return(
        <Route path={path} exact={exact} strict={strict} render={props => (
            isAuthed ? (
                <Redirect to={{
                    pathname: '/',
                    state: {from: props.location}
                }}/>
            ) : (
                <Component {...props}/>
            )
        )}/>
    );
}
