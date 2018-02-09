import java.util.*;

public class PageFault {

    /*
     * LRU algorithm, queue based.
     * Virtual pages are linked in a doubly linked list, a "queue".
     * Each time any page is referenced, it is brought to the front end of the queue.
     * In case of the page fault:
     * a page from the rear end is unmapped and new page is added to the front end.
     * */

    public static void replacePage(Vector<Page> memVector, LinkedList<Page> mappedPagesQueue, int replacePageNum, ControlPanel controlPanel) {
        int lruPageID = mappedPagesQueue.size() - 1; // least recently used page is in the end of the queue
        controlPanel.removePhysicalPage(mappedPagesQueue.getLast().id); // remove mapping in GUI
        Page lruPage = mappedPagesQueue.remove(lruPageID); // remove lru page from the queue

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
        mappedPagesQueue.offerFirst(newPage); // bring newly mapped page to the front end of the queue
    }
}
