import { apiInstance } from "./index.js";

const api = apiInstance();

async function login(user, success, fail) {
  await api
    .post(`/user/login`, JSON.stringify(user))
    .then(success)
    // .then(success, function (res) {
    //   if (res.data.JSESSIONID) {
    //     console.log("^^");
    //     this.$cookies.set("JSESSIONID", res.data.JSESSIONID, "1MIN");
    //   }
    // })
    .catch(fail);
}

async function findById(userid, success, fail) {
  api.defaults.headers["access-token"] = sessionStorage.getItem("access-token");
  await api.get(`/user/info/${userid}`).then(success).catch(fail);
}

async function modifyMember(user, success, fail) {
  await api
    .post(`/user/${user.userid}`, JSON.stringify(user))
    .then(success)
    .catch(fail);
}

async function insertMember(user, success, fail) {
  await api
    .post(`/user/memberInsert`, JSON.stringify(user))
    .then(success)
    .catch(fail);
}

function deleteMember(userid, success, fail) {
  api.delete(`/user/${userid}`).then(success).catch(fail);
}
// function logout(success, fail)

export { login, findById, modifyMember, insertMember, deleteMember };
