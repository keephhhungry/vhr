<template>
    <div id="app">
        <div class="sidebar">
            <card></card>
            <list></list>
        </div>
        <div class="main">
            <message></message>
            <usertext></usertext>
        </div>
    </div>
</template>

<script>

import card from '../../components/chat/card.vue'
import list from '../../components/chat/list.vue'
import message from '../../components/chat/message.vue'
import usertext from '../../components/chat/usertext.vue'

export default {
    name: "FriendChat",
    data () {
        return {

        }
    },
    mounted:function() {
        this.$store.dispatch('initData');
    },
    components:{
        card,
        list,
        message,
        usertext
    }
}
</script>

<style scoped>

</style>