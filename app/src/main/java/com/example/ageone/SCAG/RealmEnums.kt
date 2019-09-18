package com.example.ageone.SCAG

class Enums {

	// MARK: Enum OrderType

	enum class OrderType {
		vipAccess12M, product48H, vipAccess6M, product12M, vipAccess3M, product1M, productSet12M, productSet1M
	}

	// MARK: Enum UserType

	enum class UserType {
		clint, admin
	}

	// MARK: Enum AnnounceType

	enum class AnnounceType {
		video, event
	}

	// MARK: Enum ProductType

	enum class ProductType {
		from7To25, from25To45, lessThen7
	}

	// MARK: Enum DocumentType

	enum class DocumentType {
		regular, FAQ
	}

	// MARK: Enum CategoryType

	enum class CategoryType {
		flowers, food
	}

	// MARK: Enum PaymentType

	enum class PaymentType {
		card, applePay, cash, cardToCourier
	}

}