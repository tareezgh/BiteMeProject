package logic;

public enum MessageType {
	IpAddress, logIn, logOut, Error, getUserDetails, getAllRestaurants, getMealsInRestaurant, getMealPriceItems,
	getDeliveryDetails, getOrderDetailsByID, addOrder, addDeliveryDetails, getAllCustomerDetails, changeCustomerStatus,
	getAllRequstionForBuisnesAccount, confirmBusinessAccount, changeOrderStatus, addMealToMenu, UpdateMealsPriceInMenu,
	DeleteMealFromMenu, registerNewEmployer, getAllOrdersDetails, addRefund, getRefundDetails, UpdateRefundDetails,
	getEmployeeDetails, changeEmployerStatus, usersCheckExist, getCustomerDetails,
	getRestaurantDetails, getOrderDetailsByIdAndOrderNum, getRestaurantDetailsAsUsers, getCustomersDetailsAsUsers,
	changeCustomerAuthorization, changeRestaurantAuthorization, sendFile, updatePrivateAccount, getLogs,
	getAllRestaurantsFullName, addLogs, UpdateAveragePrepareTime, getAllRestaurantsDetails, changeRestaurantStatus,
	changeOrderApproveTime, getFiles;

}
