package com.gvssimux.util;


import lombok.Data;
import lombok.extern.java.Log;
import org.hyperledger.fabric.gateway.*;

import java.nio.file.Path;
import java.nio.file.Paths;

@Data
@Log
public class FabricUtil<T> {
    static {
        System.setProperty("org.hyperledger.fabric.sdk.service_discovery.as_localhost", "true");
    }

    static String  CCP = "/usr/software/fabric-samples/test-network/organizations/peerOrganizations/org1.example.com" +
            "/connection-org1.yaml";

    static Path walletPath = Paths.get("/usr/software/Fabric_TraceabilitySys/wallet");


    /**
     * create a gateway connection
     */
    public static Gateway createGateway(Path walletPath,String CCP) throws Exception{
        Wallet wallet = Wallets.newFileSystemWallet(walletPath);
        Path networkConfigPath = Paths.get(CCP);
        Gateway.Builder builder = Gateway.createBuilder();
        builder.identity(wallet, "appUser").networkConfig(networkConfigPath).discovery(true);
        // create a gateway connection
        log.info("创建getway");
        try (Gateway gateway = builder.connect()) {
            return gateway;
        }
    }

    /**
     * get the network
     * 如  "mychannel"
     */
    public static Network createNetwork(Path walletPath,String CCP,String channel) throws Exception{
        Network network = createGateway(walletPath,CCP).getNetwork(channel);
        log.info("创建network");
        return network;
    }


    /**
     * get the contract
     * 如 "teaArea-tea-java"
     */
    public static Contract createContract(Path walletPath,String CCP,String channel,String chaincodeId) throws Exception{
        Contract contract = createNetwork(walletPath,CCP,channel).getContract(chaincodeId);
        log.info("获取合约");
        return contract;
    }

    /**
     * 直接拿到 contract 去操作
     * */
    public static Contract getContract() throws Exception {
        Contract contract = createContract(walletPath,CCP,"mychannel","teaArea-java-demo");
        return contract;
    }










}
