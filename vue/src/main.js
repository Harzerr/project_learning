import { createApp } from 'vue'
import App from './App.vue'
import 'element-plus/dist/index.css'
import router from './router'
import ElementPlus from 'element-plus'
//
// import './assets/main.css'
import './assets/global.css'
const app = createApp(App)

app.use(router)
app.use(ElementPlus)
app.mount('#app')
//createApp(App).use(router).mount('#app')