import request from "@/utils/request.js";

export const loginAPI = (form) =>{
    return request.post('/api/user/login', form)
}

export const registerAPI = (form) =>{
    return request.post('/api/user/register', form)
}