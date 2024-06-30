package festival.deandreamatias.com.domain

/**
 * Interface to navigate between settings screens.
 */
interface ScreenNavigator {

    /**
     * Opens the screen to request the exact alarm permission.
     */
    fun openExactAlarmPermissionScreen()

    /**
     * Opens the app settings screen.
     */
    fun openAppSettings()
}
