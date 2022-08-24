import Vue from 'vue'
import VueRouter from 'vue-router'
import Home from '../views/Home'
import Login from '../views/Login'

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
    hidden:true
  }
]

const router = new VueRouter({
  routes
})

export default router
