import java.util.*;

public class PageFault {

    /*
     * LRU algorithm, queue based.
     * Virtual pages are placed in a queue.
     * Each time any page is referenced, it is brought to the rear end of the queue.
     * In case of the page fault:
     * a page from the front end is unmapped and new page is added to the rear end.
     * So we have a queue of pages to be replaced.
     * */

    public static void replacePage(Vector<Page> memVector, Queue<Page> mappedPagesQueue, int replacePageNum, ControlPanel controlPanel) {
        int lruPageID = mappedPagesQueue.peek().id; // least recently used page is in the front of the queue
        controlPanel.removePhysicalPage(lruPageID); // remove mapping in GUI
        Page lruPage = mappedPagesQueue.remove(); // remove lru page from the queue

        Page newPage = memVector.get(replacePageNum);
        if (newPage != null) {
            newPage.physical = lruPage.physical; // re-mapping
            controlPanel.addPhysicalPage(newPage.physical, replacePageNum); // re-mapping in GUI

            // reset unmapped lru page
            lruPage.inMemTime = 0;
            lruPage.lastTouchTime = 0;
            lruPage.R = 0;
            lruPage.M = 0;
            lruPage.physical = -1;
        }
        mappedPagesQueue.add(newPage); // bring newly mapped page to the rear end of the queue
    }
}
