package com.futureme.crud_supabase.presentation.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.futureme.crud_supabase.presentation.future.addproduct.AddProductScreen
import com.futureme.crud_supabase.presentation.future.productdetails.ProductDetailsScreen
import com.futureme.crud_supabase.presentation.future.productlist.ProductListScreen
import com.futureme.crud_supabase.presentation.future.signin.SignInScreen
import com.futureme.crud_supabase.presentation.future.signup.SignUpScreen


fun NavGraphBuilder.navRegistration(navController: NavController) {
    composable(ProductListDestination.route) {
        ProductListScreen(
            navController = navController
        )
    }

    composable(AuthenticationDestination.route) {
        SignInScreen(
            navController = navController
        )
    }

    composable(SignUpDestination.route) {
        SignUpScreen(
            navController = navController
        )
    }

    composable(AddProductDestination.route) {
        AddProductScreen(
            navController = navController
        )
    }

    composable(
        route = "${ProductDetailsDestination.route}/{${ProductDetailsDestination.productId}}",
        arguments = ProductDetailsDestination.arguments
    ) { navBackStackEntry ->
        val productId =
            navBackStackEntry.arguments?.getString(ProductDetailsDestination.productId)
        ProductDetailsScreen(
            productId = productId,
            navController = navController,
        )
    }

}