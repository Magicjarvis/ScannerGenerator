import java.util.ArrayList;
import java.util.List;
import java.util.Set;


public class NFAState {

  public static class Builder {

    private Set<Character> transition;
    private List<NFAState> nextStates;
    private boolean accept;

    public Builder() {
      nextStates = new ArrayList<NFAState>();
    }

    /**
     * null means that it accepts epsilons
     * @param transition
     */
    public Builder setTransition(Set<Character> transition) {
      this.transition = transition;
      return this;
    }

    public Builder setAccept(boolean accept) {
      this.accept = accept;
      return this;
    }

    public Builder setNextStates(List<NFAState> nextStates) {
      this.nextStates = nextStates;
      return this;
    }

    public Builder addNextState(NFAState s) {
      nextStates.add(s);
      return this;
    }

    public NFAState build() {
      return new NFAState(this);
    }

  }

  private Set<Character> transition;
  private List<NFAState> nextStates;
  private boolean accept;

  private NFAState(Builder b) {
    this.accept = b.accept;
    this.transition = b.transition;
    this.nextStates = b.nextStates;
  }

  public static Builder builder() {
    return new Builder();
  }

  /**
   * The states we enter after accepting a character
   * @param c
   * @return list of states we go to from c
   */
  public List<NFAState> next(Character c) {
    List<NFAState> states = new ArrayList<NFAState>();
    for (NFAState n : nextStates) {
      if (n.acceptsChar(c)) {
        states.add(n);
      }
    }
    return states;
  }


  public boolean accepts(String str) {
    // TODO(bryansills):FINISH THIS
    if (str.length() == 0 && this.isAccept()) {
      return true;
    }
    Character next = str.charAt(0);

    return false;
  }

  /**
   * Get the characters this state will accept
   * @return
   */
  public Set<Character> getTransition() {
    return transition;
  }

  public NFAState setTransition(Set<Character> transition) {
      this.transition = transition;
      return this;
  }

  public boolean isAccept() {
    return accept;
  }

  /**
   * Tells you whether or not this state accepts a particular character.
   * @param c
   * @return
   */
  public boolean acceptsChar(Character c) {
    return transition == null && c == null
        || transition != null && transition.contains(c);
  }

  /**
   * Is this an accept state or not?
   * @param b
   */
  public NFAState setAccept(boolean b) {
    accept = b;
    return this;
  }

  /**
   * Adds a state that the current state points to.
   * @param state
   */
  public NFAState addNext(NFAState state) {
    nextStates.add(state);
    return this;
  }

  public List<NFAState> getNextStates() {
    return nextStates;
  }
}
