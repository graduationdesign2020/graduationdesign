import React, {useContext} from 'react';
import {Route, Redirect} from 'react-router-dom'
import {AuthedContext} from "../utils/authed";

export default function PrivateRoute(props){
    const {isAuthed} = useContext(AuthedContext);

    const {component: Component, path="/",exact=false,strict=false} = props;
    return (<Route path={path} exact={exact} strict={strict} render={props => (
        isAuthed ? (
            <Component {...props}/>
        ) : (
            <Redirect to={{
                pathname: '/login',
                state: {from: props.location}
            }}/>
        )
    )}/>);
}
