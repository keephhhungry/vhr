import Vue from 'vue'
import VueRouter from 'vue-router'
import Home from '../views/Home'
import Login from '../views/Login'
import FriendChat from "@/views/chat/FriendChat";

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'Login',
    component: Login,
    hidden:true
  }, {
    path: '/',
    name: 'Home',
    component: Home,
    hidden:true,
    meta:{
      roles:['admin','user']
    },
    children:[
      {
        path: '/chat',
        name: 'FriendChat',
        component: FriendChat,
        hidden:true
      }
    ]
  }
]

const router = new VueRouter({
  routes
})

export default router
