package no.me.eliasbrattli.ovinger.oving04.items;

/**
 * Created by EliasBrattli on 11/11/2016.
 */
public class Picture {
    private final int resourceId;
    private String name;
    private String description;

    public Picture(int resourceId, String name,String description){
        this.resourceId = resourceId;
        this.name = name;
        this.description = description;
    }


    public int getResourceId() {
        return resourceId;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public void setName(String name) {
        this.name = name;
    }
    @Override
    public String toString(){
        return getName();
    }
}
