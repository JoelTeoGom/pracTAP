package Observer;

public class ActionListener implements Observer {

    private Event state;

    public ActionListener(){
        state = null;
    }
    @Override
    public void update(Event state) {
        this.state = state;
        System.out.println(state.name());
    }

    public Event getState() {
        return state;
    }

    public void setState(Event state) {
        this.state = state;
    }
}
