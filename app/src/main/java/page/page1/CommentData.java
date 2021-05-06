package page.page1;

public class CommentData {

        private int s_id;
        private String course_code;
        private String prof_name;
        private String comment;

        public CommentData(int s_id, String course_code, String prof_name, String comment) {
            this.s_id = s_id;
            this.course_code = course_code;
            this.prof_name = prof_name;
            this.comment = comment;
        }

        public int getSid() {
            return s_id;
        }

        public String getCourse_code() {
            return course_code;
        }
        public String getProf() {
        return prof_name;
    }
        public String getComment() {
        return comment;
    }

        public void setProf_name() {
            this.prof_name = prof_name;
        }

        public void setComment(String content) {
            this.comment = content;
        }

}
