import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.gvssimux.fabricgateway.FabricGateway;
import com.gvssimux.pojo.GeneralChainCode;
import com.gvssimux.pojo.QueryResult;
import com.gvssimux.pojo.QueryResultList;
import org.hyperledger.fabric.client.Contract;
import org.hyperledger.fabric.client.Status;
import org.hyperledger.fabric.client.SubmittedTransaction;
import org.junit.Test;

import java.util.List;

public class TestConnToNetworkByFabricGateway {

    @Test
    public void test01() throws Exception {
        FabricGateway fabricGateway = new FabricGateway();
        Contract contract = fabricGateway.getContract();
        SubmittedTransaction submittedTransaction = contract.newProposal("queryData")
                .addArguments("FabricGeneralChainCode")//参数
                .build()
                .endorse()
                .submitAsync();
        System.out.println("contract=======>"+contract);
        System.out.println();

        byte[] result1 = submittedTransaction.getResult();
        String s = new String(result1);
        System.out.println("初始数据=======>"+s);
        System.out.println();

        Status status = submittedTransaction.getStatus();
        byte[] result = submittedTransaction.getResult();
        System.out.println("区块号=======>"+status.getBlockNumber());
        System.out.println();
        System.out.println("交易Id=======>"+status.getTransactionId());
        System.out.println();
        System.out.println("code=======>"+status.getCode());
        System.out.println();
        System.out.println("是否成功=======>"+status.isSuccessful());
        System.out.println();
        System.out.println("初始数据=======>"+new String(result));
        System.out.println();
    }

    @Test
    public void test02() throws Exception {
        FabricGateway fabricGateway = new FabricGateway();
        Contract contract = fabricGateway.getContract();
        byte[] bytes = contract.evaluateTransaction("queryData", "FabricGeneralChainCode");
        System.out.println("初始数据=======>"+new String(bytes));
        System.out.println();
    }

    // test03 和 test02 虽然都是查询初始数据，但是你会发现一个在你部署的 Hyperledger Explorer 中
    // 你执行test03 会形成交易，并且会打包成区块，而你test02中的调用不会
    @Test
    public void test03() throws Exception {
        FabricGateway fabricGateway = new FabricGateway();
        Contract contract = fabricGateway.getContract();
        SubmittedTransaction submittedTransaction = contract.newProposal("queryData")
                .addArguments("FabricGeneralChainCode")//参数
                .build()
                .endorse()
                .submitAsync();
        byte[] result= submittedTransaction.getResult();
        System.out.println(new String(result));
    }

    /*富查询邮箱为gvssimux@qq.com*/
    @Test
    public void test04() throws Exception {
        FabricGateway fabricGateway = new FabricGateway();
        Contract contract = fabricGateway.getContract();
        String str = "{\"selector\":{\"email\":\"gvssimux@qq.com\"}, \"use_index\":[]}";// 富查询字符串
        byte[] richQueries = contract.submitTransaction("richQuery", str);
        String s = new String(richQueries);
        System.out.println(new String(richQueries));
    }


    /*富查询邮箱为gvssimux@qq.com 的作者*/
    @Test
    public void test05() throws Exception {
        FabricGateway fabricGateway = new FabricGateway();
        Contract contract = fabricGateway.getContract();
        String str = "{\"selector\":{\"email\":\"gvssimux@qq.com\"}, \"use_index\":[]}";// 富查询字符串
        byte[] richQueries = contract.submitTransaction("richQuery", str);
        String s = new String(richQueries);
        JSONObject jsonObject = JSONObject.parseObject(s);
        QueryResultList resultList = JSON.toJavaObject(jsonObject, QueryResultList.class);
        List<QueryResult> resultValueList = resultList.getResultList();
        for (QueryResult a: resultValueList) {
            System.out.println("for循环打印");
            String json = a.getJson();
//            System.out.println(json);

            JSONObject jsonObject2 = JSONObject.parseObject(json);
            GeneralChainCode data = JSON.toJavaObject(jsonObject2, GeneralChainCode.class);

            System.out.println(data.getUrl());
            System.out.println(data.getEmail());
            System.out.println(data.getAuthor());
        }
//        System.out.println();
//        System.out.println("====>"+resultList);
//        System.out.println();
//        System.out.println(new String(richQueries));
    }
}
