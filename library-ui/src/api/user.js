import request from "@/utils/request.js";

export const userInfoAPI = (token) => {
    return request({
        url: `/api/users`,
        params:{
            token,
        }
    })
}

export const getUsersAPI = ({pageNum,pageSize,name,phone,email}) => {
    return request({
        url: `/api/users/search`,
        params:{
            pageNum,
            pageSize,
            name,
            phone,
            email,
        },
    })
}

export const updateUserAPI = (form) => {
    return request.put(`/api/users/${form.id}`, form)
}

export const deleteUserAPI = (id) => {
    return request.delete(`/api/users/${id}`)
}

export const batchDeleteUsersAPI = (ids) => {
    return request.post('api/users/deleteBatch',ids)
}