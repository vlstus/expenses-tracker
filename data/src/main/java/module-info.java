module data {
    requires static lombok;

    exports org.stus.tracker.model to web;
    exports org.stus.tracker.repository to web;
}