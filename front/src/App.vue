<template>
  <div id="app">
      <!-- header navbar -->
      <nav class="navbar text-bg-dark">
          <div class="container-fluid">
              <a class="navbar-brand" href="#">
                  <img src="public/logo.png" alt="Logo" width="30" height="24" class="d-inline-block align-text-top">
                  <span class="text-light">Responseman</span>
              </a>
          </div>
      </nav>

      <div class="container-fluid">
          <div class="row">
              <servers-sidebar></servers-sidebar>
              <detail-info></detail-info>
          </div>
      </div>
  </div>
</template>

<script>
  import ServersSidebar from "./components/ServersSidebar.vue";
  import DetailInfo from "./components/DetailInfo.vue"

  export default {
      name: "app",
      components: {
          ServersSidebar,
          DetailInfo
      },
  };
</script>
