<template>
  <div>
    <b-sidebar id="sidebar-1" title="Todo List" shadow>
      <div class="px-3 py-2">
        <todo-view />
      </div>
    </b-sidebar>
  </div>
</template>

<script>
import TodoView from "@/views/TodoView.vue";

export default {
  components: { TodoView },
};
</script>

<style></style>
