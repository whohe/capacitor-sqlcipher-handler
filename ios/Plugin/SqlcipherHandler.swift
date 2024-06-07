import Foundation

@objc public class SqlcipherHandler: NSObject {
    @objc public func echo(_ value: String) -> String {
        print(value)
        return value
    }
}
