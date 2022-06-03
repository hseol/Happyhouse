<template>
  <div>
    <button @click="buyHouse">이 아파트 매입</button>
  </div>
</template>

<script>
import { ethers } from "ethers";
export default {
  name: "EtherTest",
  props: {
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
          to: "0x41A4D1B6C3c7476521E4a32B6171861C6741186b", //매도자(현재 소유주):변경 요!!!
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
        address: "0xc3c06401133687Cb17F170614b471B2135Ea6c00", //스마트 컨트랙트 주소:변경 요!!!
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

<style></style>
