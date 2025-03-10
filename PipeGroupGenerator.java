import java.util.ArrayList;
import java.util.Iterator;

public class PipeGroupGenerator {
  ArrayList<PipeGroup> pipeGroups = new ArrayList<PipeGroup>();
  int counter = 0;
  int COUNT_TO_GENERATE_PIPE_GROUP = 100;

  Screen screen;
  PipeGroup nextPipeGroup;
  int nextPipeIndex = 0;

  PipeGroupGenerator(Screen screen) {
    this.screen = screen;
    this.nextPipeGroup = this.addPipeGroup();
  }

  public void update() {
    Iterator<PipeGroup> i = this.pipeGroups.iterator();

    PipeGroup pipeToBeRemoved = null;

    while(i.hasNext()) {
      PipeGroup pipeGroup = i.next();
      
      pipeGroup.update();

      if (pipeGroup.hitOutside()) {
        pipeToBeRemoved = pipeGroup;
      }
    }

    if (pipeToBeRemoved != null) {
      this.pipeGroups.remove(pipeToBeRemoved);
      pipeToBeRemoved.removeFromScreen();
      this.nextPipeIndex -= 1;
    }

    this.counter += 1;

    if (this.counter > this.COUNT_TO_GENERATE_PIPE_GROUP) {
      this.counter = 0;
      this.addPipeGroup();
    }
  }

  public boolean hitNextPipe(int x, int y, int dist) {
    return this.nextPipeGroup.hitMe(x, y, dist);
  }

  public boolean passNextPipe(int x, int y, int dist) {
    boolean result = this.nextPipeGroup.passMe(x, y, dist);

    if (result) {
      this.nextPipeIndex += 1;
      this.nextPipeGroup = this.pipeGroups.get(this.nextPipeIndex);
    }

    return result;
  }

  private PipeGroup addPipeGroup() {
    PipeGroup group = new PipeGroup(this.screen);
    this.pipeGroups.add(group);
    group.insertIntoScreen();
    return group;
  }
}
