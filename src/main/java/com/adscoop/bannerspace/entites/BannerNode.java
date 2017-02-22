package com.adscoop.bannerspace.entites;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
<<<<<<< HEAD
import jdk.nashorn.internal.ir.annotations.Ignore;
=======
>>>>>>> 864a243ee82c65a8969aa46223a6b9d5ae852251
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;
import org.neo4j.ogm.annotations.Labels;

<<<<<<< HEAD
=======
import java.net.URL;
>>>>>>> 864a243ee82c65a8969aa46223a6b9d5ae852251
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by thokle on 24/08/2016.
 */

@NodeEntity
public class BannerNode extends Entity {


    @JsonProperty
    private String userId;
    @JsonProperty
    private String domain;
    @JsonProperty
    private String positionSiteM;
    @JsonProperty
    private String positionSiteL;
    @JsonProperty
<<<<<<< HEAD
    private String url;
=======
    private URL Url;
>>>>>>> 864a243ee82c65a8969aa46223a6b9d5ae852251
    private String uniqeToken;
    @JsonProperty
    private Integer lenght;
    @JsonProperty
    private Integer height;
    @JsonProperty
    private  String counterUrl;
    @JsonProperty
    private Integer counter;
<<<<<<< HEAD
    private List<String> bannerSpaceToken  = new ArrayList<>();
    private  String pictureUrl;
    private  String javaScriptUrl;
    private String filetype;
=======
    private List<String> bannerSpaceToken;
    private  String pictureUrl;
    private  String javaScriptUrl;

>>>>>>> 864a243ee82c65a8969aa46223a6b9d5ae852251
@Labels
private List<String> labels = new ArrayList<>();





    @Relationship(type = "HAS_CATEGORIES",direction = Relationship.OUTGOING)
    private Set<Category> categories = new HashSet<>();

    @Relationship(type = "HAS_TARGETGROUPS",direction = Relationship.OUTGOING)
    private  Set<TargetGroups> targetGroupses = new HashSet<>();


    @Relationship(type = "HAS_FOOTPRINT", direction = Relationship.OUTGOING)
    private Set<FootPrintInformationNode> footPrintInformationNodes = new HashSet<>();


<<<<<<< HEAD
    @Ignore
=======
>>>>>>> 864a243ee82c65a8969aa46223a6b9d5ae852251
    @Relationship(type = "HAS_BANNER_NODES", direction = Relationship.INCOMING)
    private Set<UserNode> userNodes  = new HashSet<>();

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getPositionSiteM() {
        return positionSiteM;
    }

    public void setPositionSiteM(String positionSiteM) {
        this.positionSiteM = positionSiteM;
    }

    public String getPositionSiteL() {
        return positionSiteL;
    }

    public void setPositionSiteL(String positionSiteL) {
        this.positionSiteL = positionSiteL;
    }

<<<<<<< HEAD
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        url = url;
=======
    public URL getUrl() {
        return Url;
    }

    public void setUrl(URL url) {
        Url = url;
>>>>>>> 864a243ee82c65a8969aa46223a6b9d5ae852251
    }

    public String getUniqeToken() {
        return uniqeToken;
    }

    public void setUniqeToken(String uniqeToken) {
        this.uniqeToken = uniqeToken;
    }

    public Integer getLenght() {
        return lenght;
    }

    public void setLenght(Integer lenght) {
        this.lenght = lenght;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public String getCounterUrl() {
        return counterUrl;
    }

    public void setCounterUrl(String counterUrl) {
        this.counterUrl = counterUrl;
    }

    public Integer getCounter() {
        return counter;
    }

    public void setCounter(Integer counter) {
        this.counter = counter;
    }

    public List<String> getLabels() {
        return labels;
    }

    public void setLabels(List<String> labels) {
        this.labels = labels;
    }


    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

    public void setFootPrintInformationNodes(FootPrintInformationNode footPrintInformationNodes){
   this.footPrintInformationNodes.add(footPrintInformationNodes);
}

    public Set<FootPrintInformationNode> getFootPrintInformationNodes() {
        return footPrintInformationNodes;
    }

    public void setLabel(String label){
  this.labels.add(label);
    }

    public void addFootPrint(FootPrintInformationNode footPrintInformationNode){
        footPrintInformationNodes.add(footPrintInformationNode);
        footPrintInformationNode.getBannerNodeSet().add(this);
    }


<<<<<<< HEAD
    public String getFiletype() {
        return filetype;
    }

    public void setFiletype(String filetype) {
        this.filetype = filetype;
    }
=======
>>>>>>> 864a243ee82c65a8969aa46223a6b9d5ae852251

    public void addCategory(Category category){
        categories.add(category);
        category.getCatbannerNodes().add(this);
    }


    public List<String> getBannerSpaceToken() {
        return bannerSpaceToken;
    }

    public void setBannerSpaceToken(List<String> bannerSpaceToken) {
        this.bannerSpaceToken = bannerSpaceToken;
    }

    public Set<Category> getCategories() {
        return categories;
    }

    public void setCategories(Set<Category> categories) {
        this.categories = categories;
    }

    public Set<TargetGroups> getTargetGroupses() {
        return targetGroupses;
    }

    public void setTargetGroupses(Set<TargetGroups> targetGroupses) {
        this.targetGroupses = targetGroupses;
    }



    public String getJavaScriptUrl() {
        return javaScriptUrl;
    }

    public void setJavaScriptUrl(String javaScriptUrl) {
        this.javaScriptUrl = javaScriptUrl;
    }

    @JsonIgnore
    public Set<UserNode> getUserNodes() {
        return userNodes;
    }

    public void setUserNodes(Set<UserNode> userNodes) {
        this.userNodes = userNodes;
    }
}
