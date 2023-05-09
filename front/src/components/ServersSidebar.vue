<template>
    <div class="col-md-4 border-end">
        <div id="titleSidebar" class="d-flex flex-column align-items-center flex-shrink-0 bg-white border-bottom" >
            <div class="d-flex align-items-center flex-shrink-0 p-3 link-dark text-decoration-none">
                <span class="fs-5 fw-semibold">서버 목록</span>
            </div>
        </div>

        <!-- "v-bind:key" 는 요소들의 정렬의 기준을 정할 때 사용, 넣지 않을 시 오류가 발생했음   -->
        <div id="serverList" class="list-group list-group-flush border-bottom" style="overflow-y: auto;">
            <div class="container text-center border-bottom">
                <div class="row">
                    <div class="col border-end">
                        Information
                    </div>
                    <div class="col">
                        Status
                    </div>
                </div>
            </div>
            <div class="list-group-item list-group-item-action py-3 lh-sm" aria-current="true" v-for="(server, idx) in servers" v-bind:key="server.id">
                <div class="d-flex w-100 align-items-center justify-content-between" @click="goServerInfo">
                    <strong class="mb-1">{{ idx + 1 }}. {{ server.name }}</strong>
                    <button type="button" class="btn btn-outline-primary">Run</button>
                </div>
                <div class="col-10 mb-1 small">{{ server.port }}</div>
            </div>
        </div>
    </div>
</template>

<script>
export default {
    /**
     * 반응형 데이터 생성. 해당 함수에 등록한 데이터는 내부 함수들에서 "this"를 통해 접근 가능
     * ex) this.servers
     */
    data() {
        return {
            servers: [{
                id: 1,
                name: 'test001',
                port: '8888',
                responseData: '테스트용 응답 데이터입니다.'
            }, {
                id: 2,
                name: 'test002',
                port: '8888',
                responseData: '테스트용 응답 데이터입니다.'
            }, {
                id: 3,
                name: 'test003',
                port: '8888',
                responseData: '테스트용 응답 데이터입니다.'
            }]
        }
    },
    /**
     * 해당 컴포넌트에서 사용할 함수 등록. DOM 이벤트를 감지하여 아래 정의한 함수를 호출할 수 있음
     * see: v-on:click="goServerInfo"
     */
    methods: {
        goServerInfo: function () {
            console.log('click test')
        }
    },
    mounted() {
        window.scrollable();
    }
}

function scrollable() {
    let height = document.documentElement.clientHeight;
    let mainHeight = document.getElementById("titleSidebar").style.height;
    document.getElementById("serverList").style.height = (height - mainHeight) + "px";
    console.log((height - mainHeight) + "px")
}

window.addEventListener("resize", function () {
    scrollable();
});
</script>

<style>


</style>
