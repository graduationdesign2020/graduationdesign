import React from "react";

export const AuthedContext = React.createContext({
    IsAuthed: false,
    AuthedSuccess: ()=>{},
    UserInfo: {},
    GetUserInfo: ()=>{},
});
