package com.ryanyi.restapi.Domain;

//POJO
public class Order implements Identifiable {

    private Long id;
    private String decription;
    private long costInCents;
    private boolean isComplete;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public String getDecription() {
        return decription;
    }

    public void setDecription(String decription) {
        this.decription = decription;
    }

    public long getCostInCents() {
        return costInCents;
    }

    public void setCostInCents(long costInCents) {
        this.costInCents = costInCents;
    }

    public boolean isComplete() {
        return isComplete;
    }

    public void setComplete(boolean complete) {
        isComplete = complete;
    }

    public void markComplete() {
        setComplete(true);
    }

    public void markInComplete() {
        setComplete(false);
    }
}
