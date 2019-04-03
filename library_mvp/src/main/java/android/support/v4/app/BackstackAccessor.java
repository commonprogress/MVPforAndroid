package android.support.v4.app;


public class BackstackAccessor {

    private BackstackAccessor() {
        throw new IllegalStateException("Not instantiatable");
    }

    /**
     * Checks whether or not a given fragment is on the backstack of the fragment manager (could also
     * be on top of the backstack and hence visible)
     *
     * @param fragment The fragment you want to check if its on the back stack
     * @return true, if the given Fragment is on the back stack, otherwise false (not on the back
     * stack)
     */
    public static boolean isFragmentOnBackStack(Fragment fragment) {
        return fragment.isInBackStack();
    }
}
