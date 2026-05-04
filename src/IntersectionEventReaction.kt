import de.th_koeln.basicstage.geoevents.IntersectionEventListener

class IntersectionReaction: IntersectionEventListener {
    override fun onStartIntersection() {
        //Wenn sie sich schneiden

        println("Actoren schneidet sich")
    }
    override fun onStopIntersection() {
        //Wenn sie aufhören sich zu schneiden
    }
}
