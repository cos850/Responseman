<template>
    <main class="col-md-8">
        <p class="text-center fs-2"> {{ server.id }}. {{ server.name }} </p>
        <div>
            <div class="mb-3 row">
                <label for="input" class="col-sm-2 col-form-label">PORT</label>
                <div class="col-sm-10">
                    <input type="text" class="form-control" id="port">
                </div>
            </div>
            <div class="mb-3 row">
                <label for="input" class="col-sm-2 col-form-label">Response Data</label>
                <div class="col-sm-10">
                    <textarea class="form-control" id="exampleFormControlTextarea1" rows="7"></textarea>
                </div>
            </div>
        </div>
    </main>
</template>

<script>
    export default {
        data() {
            return {
                server: {
                    id: 1,
                    name: 'test001',
                    port: '8888',
                    responseData: '테스트용 응답 데이터입니다.'
                }
            }
        },
        mounted() {

        }
    }
</script>

<style>


</style>
