package client;

import gui.*;

/**
 * a class to save a reference to all the controllers so we can access them
 * quickly
 *
 */
public class ScreenControllers {

	public static IpFormController ipFormController;
	public static LoginFormController loginFormController;

	public static BMManagerFormController ManagerFormController;
	public static BMManagerApproveRegistrationFormController ManagerApproveRegistrationFormController;
	public static BMManagerManageDataFormController ManagerManageDataFormController;
	public static BMManagerRegisterFormController ManagerRegisterFormController;
	public static BMManagerViewLogsFormController ManagerViewLogsFormController;

	public static RestaurantFormController restaurantFormController;
	public static RestaurantApproveOrderFormController restaurantApproveOrderFormController;
	public static RestaurantUpdateMenuFormController restaurantCreateUpdateMenuFormController;

	public static CustomerFormController customerFormController;
	public static CustomerChooseRestaurantFormController customerChooseRestaurantFormController;
	public static CustomerMenuFormController customerMenuFormController;
	public static CustomerDeliveryFormController customerDeliveryFormController;
	public static CustomerFinalOrderFormController customerFinalOrderFormController;

	
	public static CustomerViewOrderFormController customerViewOrderFormController;

	public static HRFormController hrFormController;
	public static HRConfirmAccountsFormController hrConfirmAccountsFormController;
	public static HRRegisterEmployeeFormController hrRegisterEmployeeFormFormController;

	public static CEOFormController ceoFormController;
	public static CEOCompareLogsFormController ceoCompareLogsFormController;
	public static CEOViewLogsIncomeFormController ceoViewLogsFormFormController;

}
