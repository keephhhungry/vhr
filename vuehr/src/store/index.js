import Vue from 'vue'
import Vuex from 'vuex'
import { Notification } from 'element-ui';
import {getRequest} from "../utils/api";

Vue.use(Vuex)

const store = new Vuex.Store({
    state : {
        routes: [],
    },
    mutations: {
        increment (state) {
            state.count++
        },
        initRoutes(state,data){
            state.routes = data;
        }
    }
})

export default store;