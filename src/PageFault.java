import java.util.*;

public class PageFault {

    /*
     * LRU algorithm, queue based.
     * Virtual pages are linked in a doubly linked list, a "queue".
     * Each time any page is referenced, it is brought to the front end of the queue.
     * If queue is full (every of th available virtual pages are mapped to physical ones),
     * a page from the rear end is unmapped and new page is added to the front end.
     * */

    private static Page getPageByID(LinkedList<Page> memQueue, int pageID, int virtPageNum) {
        for (int i = 0; i <= virtPageNum; i++) {
            if (memQueue.get(i).id == pageID) return memQueue.get(i);
        }
        return null;
    }

    public static void replacePage(LinkedList<Page> mem, int virtPageNum, int replacePageNum, ControlPanel controlPanel, LinkedList<Page> all) {
        int lruPageID = mem.size() - 1; // least recently used page is in the end of the queue
        Page newPage = getPageByID(all, replacePageNum, virtPageNum);
        controlPanel.removePhysicalPage(mem.getLast().id);
        Page lruPage = mem.remove(lruPageID);
        if (newPage != null) {
            newPage.physical = lruPage.physical;
            controlPanel.addPhysicalPage(newPage.physical, replacePageNum);
            lruPage.inMemTime = 0;
            lruPage.lastTouchTime = 0;
            lruPage.R = 0;
            lruPage.M = 0;
            lruPage.physical = -1;
        }
        mem.offerFirst(newPage);
    }
}
