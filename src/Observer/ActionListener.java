package Observer;

public class ActionListener implements Observer {

    private Event state;

    /**
     * constructor ActionListener
     */
    public ActionListener(){
        state = null;
    }

    /**
     * metoded per actualitzar el estat del ActionListener
     * @param state
     */
    @Override
    public void update(Event state) {
        this.state = state;
        System.out.println(state.name());
    }

    /**
     * getter del estat
     * @return
     */

    public Event getState() {
        return state;
    }

    /**
     * setter del estat
     * @param state
     */
    public void setState(Event state) {
        this.state = state;
    }
}
