package com.jerry86189.artifitialmanagement.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

/**
 * ClassName: Model
 * Description: TODO
 * date: 2023/06/12 18:52
 *
 * @author Jerry
 * @version 1.0
 * @since JDK 1.8
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("model")
public class Model implements Serializable {
    @TableId(value = "model_id", type = IdType.AUTO)
    private Long modelId;

    @TableField("x_names_json")
    private String xNamesJson;

    @TableField("y_names_json")
    private String yNamesJson;

    @TableField("deep_cnn_hy_para_json")
    private String deepCnnHyParaJson;

    @TableField("knn_hy_para_json")
    private String knnHyParaJson;

    @TableField("nn_hy_para_json")
    private String nnHyParaJson;

    @TableField(exist = false)
    private DeepCnnHyPara deepCnnHyPara;

    @TableField(exist = false)
    private KnnHyPara knnHyPara;

    @TableField(exist = false)
    private NNHyPara nnHyPara;

    @TableField(exist = false)
    private List<String> xNames;

    @TableField(exist = false)
    private List<String> yNames;

    public void setXNames(List<String> xNames) {
        this.xNames = xNames;
        ObjectMapper mapper = new ObjectMapper();
        try {
            this.xNamesJson = mapper.writeValueAsString(xNames);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    public List<String> getXNames() {
        if (this.xNames == null && this.xNamesJson != null) {
            ObjectMapper mapper = new ObjectMapper();
            try {
                this.xNames = mapper.readValue(this.xNamesJson, new TypeReference<List<String>>(){});
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return this.xNames;
    }

    public void setYNames(List<String> yNames) {
        this.yNames = yNames;
        ObjectMapper mapper = new ObjectMapper();
        try {
            this.yNamesJson = mapper.writeValueAsString(yNames);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    public List<String> getYNames() {
        if (this.yNames == null && this.yNamesJson != null) {
            ObjectMapper mapper = new ObjectMapper();
            try {
                this.yNames = mapper.readValue(this.yNamesJson, new TypeReference<List<String>>(){});
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return this.yNames;
    }

    public void setDeepCnnHyPara(DeepCnnHyPara deepCnnHyPara) {
        this.deepCnnHyPara = deepCnnHyPara;
        ObjectMapper mapper = new ObjectMapper();
        try {
            this.deepCnnHyParaJson = mapper.writeValueAsString(deepCnnHyPara);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    public DeepCnnHyPara getDeepCnnHyPara() {
        if (this.deepCnnHyPara == null && this.deepCnnHyParaJson != null) {
            ObjectMapper mapper = new ObjectMapper();
            try {
                this.deepCnnHyPara = mapper.readValue(this.deepCnnHyParaJson, DeepCnnHyPara.class);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return this.deepCnnHyPara;
    }

    public void setKnnHyPara(KnnHyPara knnHyPara) {
        this.knnHyPara = knnHyPara;
        ObjectMapper mapper = new ObjectMapper();
        try {
            this.knnHyParaJson = mapper.writeValueAsString(knnHyPara);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    public KnnHyPara getKnnHyPara() {
        if (this.knnHyPara == null && this.knnHyParaJson != null) {
            ObjectMapper mapper = new ObjectMapper();
            try {
                this.knnHyPara = mapper.readValue(this.knnHyParaJson, KnnHyPara.class);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return this.knnHyPara;
    }

    public void setNnHyPara(NNHyPara nnHyPara) {
        this.nnHyPara = nnHyPara;
        ObjectMapper mapper = new ObjectMapper();
        try {
            this.nnHyParaJson = mapper.writeValueAsString(nnHyPara);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    public NNHyPara getNnHyPara() {
        if (this.nnHyPara == null && this.nnHyParaJson != null) {
            ObjectMapper mapper = new ObjectMapper();
            try {
                this.nnHyPara = mapper.readValue(this.nnHyParaJson, NNHyPara.class);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return this.nnHyPara;
    }
}
