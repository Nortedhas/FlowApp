package com.example.ageone.SCAG
class Enums {

	// MARK: Enum ProductType

	enum class ProductType {
		From25To45, From7To25, LessThen7
	}

	// MARK: Enum OrderType

	enum class OrderType {
		Accepted, Cancelled, Created
	}

	// MARK: Enum UserType

	enum class UserType {
		Clint, Admin
	}

	// MARK: Enum AnnounceType

	enum class AnnounceType {
		Event, Video
	}

	// MARK: Enum PaymentType

	enum class PaymentType {
		Cash, Card, ApplePay, CardToCourier
	}

	// MARK: Enum DocumentType

	enum class DocumentType {
		Regular, FAQ
	}

	// MARK: Enum CategoryType

	enum class CategoryType {
		Flowers, Food
	}

}