import { apiInstance } from "./index.js";
const api = apiInstance();

function searchStore(keyword, success, fail) {
  api.get(`/store/${keyword}`).then(success).catch(fail);
}

function listStore(param, success, fail) {
  api.get(`/store`, { params: param }).then(success).catch(fail);
}

export { listStore, searchStore };
