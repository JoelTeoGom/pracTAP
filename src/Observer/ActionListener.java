package Observer;

public class ActionListener implements Observer {

    private String state;

    public ActionListener(){
        state = null;
    }
    @Override
    public void update(String state) {
        this.state = state;
        System.out.println(state);
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
