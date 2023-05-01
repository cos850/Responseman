<template>
  <div id="app">
    <servers-sidebar></servers-sidebar>
  </div>
</template>

<script>
  import ServersSidebar from "./components/ServersSidebar.vue";

  export default {
      name: "app",
      components: {
          ServersSidebar,
      },
  };
</script>
