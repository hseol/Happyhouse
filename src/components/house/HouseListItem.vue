<template>
  <b-row
    class="m-2"
    @click="selectHouse"
    @mouseover="colorChange(true)"
    @mouseout="colorChange(false)"
    :class="{ 'mouse-over-bgcolor': isColor }"
  >
    <b-media>
      <b-media-body>
        <h5 style="color: #ffcc66">
          <b-icon-geo-alt-fill></b-icon-geo-alt-fill> {{ house.apartmentName }}
        </h5>
        <h6>- 거래금액 :{{ house.recentPrice }}만원</h6>
        <!-- <h6>- 건축년도 :{{ house.buildYear }}년</h6> -->
        <h6>
          - 면적 : {{ house.area }}m² ({{ parseInt(house.area / 3.3058) }}
          평)
        </h6>
        <p>
          <b-icon-calendar3></b-icon-calendar3> {{ house.dealYear }}.{{
            house.dealMonth
          }}.{{ house.dealDay }}
        </p>
        <b-button @click="buyHouse">이 아파트 매입</b-button>
      </b-media-body>
    </b-media>
    <!-- <b-col cols="2" class="text-center align-self-center">
      <b-img
        thumbnail
        src="https://picsum.photos/250/250/?image=58"
        alt="Image 1"
      ></b-img>
    </b-col> -->
  </b-row>
</template>

<script>
import { mapActions } from "vuex";
import { ethers } from "ethers";
const houseStore = "houseStore";

export default {
  name: "HouseListItem",
  data() {
    return {
      isColor: false,
      hou: {},
    };
  },
  props: {
    house: Object,
    msg: String,
  },
  methods: {
    buyHouse() {
      console.log(ethers);
      if (typeof window.ethereum !== "undefined") {
        console.log("MetaMask is installed!");
        eth_tx();
      }
    },
    ...mapActions(houseStore, ["detailHouse"]),
    selectHouse() {
      console.log(this.house);
      this.detailHouse(this.house);
    },
    colorChange(flag) {
      this.isColor = flag;
    },
  },
  created() {
    this.hou = this.house;
  },
};

async function eth_tx() {
  //console.log(web3);
  const accounts = await window.ethereum.request({
    method: "eth_requestAccounts",
  });
  const account = accounts[0];
  console.log(account);

  window.ethereum
    .request({
      method: "eth_sendTransaction",
      params: [
        {
          from: accounts[0], //매입자
          to: "0x1e7EBa541DE587D4724b14952Af49e9b91D75D8D", //매도자(현재 소유주):변경 요!!!
          value: "0x29a2241af62c0000", //매입가 3ETH
          gasPrice: "0x09184e72a000",
          gas: "0x2710",
        },
      ],
    })
    .then(async (txHash) => {
      console.log(txHash);
      const provider = new ethers.providers.Web3Provider(window.ethereum);
      await provider.send("eth_requestAccounts", []);
      const signer = provider.getSigner();
      console.log("singer:", signer);
      const usdc = {
        address: "0x32ff20E6b3a8EA8C914437d0A958a26750976EEe", //스마트 컨트랙트 주소:변경 요!!!
        abi: [
          "function buyRealEstate(uint _id, address _buyerAddress, uint _price) public  returns (string)",
        ],
      };
      let userAddress = await signer.getAddress();
      const usdcContract = new ethers.Contract(usdc.address, usdc.abi, signer);
      console.log(usdcContract);
      const tx = await usdcContract.buyRealEstate(101, userAddress, 3);
      console.log(`buyRealEstate Transaction hash: ${tx.hash}`);

      const result = await tx.wait();
      console.log(`Transaction confirmed in block ${result.blockNumber}`);
      alert("거래가 성공되었습니다");
    })
    .catch((error) => console.log(error));
}
</script>

<style scoped>
.apt {
  width: 50px;
}
.mouse-over-bgcolor {
  background-color: lightblue;
}
</style>
