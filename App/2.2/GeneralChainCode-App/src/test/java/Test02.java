import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.gvssimux.fabricgateway.FabricGateway;
import com.gvssimux.pojo.GeneralChainCode;
import com.gvssimux.pojo.QueryResult;
import com.gvssimux.pojo.QueryResultList;
import org.hyperledger.fabric.client.Contract;
import org.junit.Test;

import java.util.List;

public class Test02 {

    /*测试提交数据*/
    @Test
    public void test1() throws Exception {
        FabricGateway fabricGateway = new FabricGateway();
        Contract contract = fabricGateway.getContract();
        String values = "{\"author\":\"ShanZJ002\",\"email\":\"gvssimux@qq.com\",\"name\":\"GeneralChainCode\",\"url\":\"www.gvssimux.com\"}";
        contract.submitTransaction("createData","test003",values);
    }

    /*测试查询数据*/
    @Test
    public void test2() throws Exception {
        FabricGateway fabricGateway = new FabricGateway();
        Contract contract = fabricGateway.getContract();
        String values = "{\"author\":\"ShanZJ002\",\"email\":\"gvssimux@qq.com\",\"name\":\"GeneralChainCode\",\"url\":\"www.gvssimux.com\"}";
        byte[] bytes = contract.evaluateTransaction("queryData", "test001");
    }


    /*测试修改数据*/
    @Test
    public void test3() throws Exception {
        FabricGateway fabricGateway = new FabricGateway();
        Contract contract = fabricGateway.getContract();
        String values = "{\"author\":\"Gvssimux\",\"email\":\"simux@qq.com\",\"name\":\"GeneralChainCode\",\"url\":\"www.simux.com\"}";
        contract.submitTransaction("updateData","test001",values);
    }


    /*测试删除数据*/
    @Test
    public void test4() throws Exception {
        FabricGateway fabricGateway = new FabricGateway();
        Contract contract = fabricGateway.getContract();
        String values = "{\"author\":\"ShanZJ002\",\"email\":\"gvssimux@qq.com\",\"name\":\"GeneralChainCode\",\"url\":\"www.gvssimux.com\"}";
        contract.submitTransaction("deleteData","test001");
    }


    /*测试富查询数据*/
    /*富查询邮箱为gvssimux@qq.com 的作者*/
    @Test
    public void test5() throws Exception {
        FabricGateway fabricGateway = new FabricGateway();
        Contract contract = fabricGateway.getContract();
        String str = "{\"selector\":{\"email\":\"gvssimux@qq.com\"}, \"use_index\":[]}";// 富查询字符串
        byte[] richQueries = contract.submitTransaction("richQuery", str);
        JSONObject jsonObject = JSONObject.parseObject(new String(richQueries));
        List<QueryResult> resultValueList = JSON.toJavaObject(jsonObject, QueryResultList.class).getResultList();
        for (QueryResult a: resultValueList) {
            System.out.println("for循环打印");
            JSONObject jsonObject2 = JSONObject.parseObject(a.getJson());
            GeneralChainCode data = JSON.toJavaObject(jsonObject2, GeneralChainCode.class);
            System.out.println(data.getUrl());
            System.out.println(data.getEmail());
            System.out.println(data.getAuthor());
        }
    }
}


