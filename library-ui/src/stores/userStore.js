import {defineStore} from "pinia";
import {ref} from "vue";
import {userInfoAPI} from "@/api/user.js";
import user from "@element-plus/icons/lib/User.js";


export const useUserStore = defineStore('user',()=>{
    const userInfo = ref({});
    const getUserInfo = async() => {
        const token = localStorage.getItem('token');
        const res =  await userInfoAPI(token);
        userInfo.value = res.data.data;
    }

    const clearUserInfo = () => {
        localStorage.removeItem('token');
        userInfo.value = {};
    }
    return {userInfo,clearUserInfo,getUserInfo};
    }, {
    persist: true,
})

