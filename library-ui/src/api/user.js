import request from "@/utils/request.js";

export const userInfoAPI = (token) => {
    return request({
        url: `/api/users`,
        params:{
            token,
        }
    })
}