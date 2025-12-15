/*
 * @author Muhammad Ubaid Ur Raheem Ahmad AKA Shahbaz Haroon
 * Email: shahbazhrn@gmail.com
 * Cell: +923002585925
 * GitHub: https://github.com/ShahbazHaroon
 */

package com.ubaidsample.h2.enums;

public enum MaskSensitiveDataCategory {
    PII,          // GDPR personal data
    PCI,          // Credit card  / payment data
    AUTH,         // Tokens, passwords
    SECRET        // API keys, secrets
}